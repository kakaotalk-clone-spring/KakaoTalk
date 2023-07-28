# import config.
# You can change the default config with `make cnf="config_special.config" build`
cnf ?= docker.config.env
include $(cnf)
export $(shell sed 's/=.*//' $(cnf))

# HELP
# This will output the help for each task
# thanks to https://marmelab.com/blog/2016/02/29/auto-documented-makefile.html
.PHONY: help

help: ## This help.
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

.DEFAULT_GOAL := help

# SPRING
# Build the project
bootjar:
	./gradlew bootJar

# DOCKER TASKS
# Build the container
build-dev: ## Build the container
	docker build --platform $(PLATFORM) -t $(APP_NAME) .

build-prod: ## Build the container
	docker build --platform $(PLATFORM) -t $(APP_NAME) .

build-nc: ## Build the container without caching
	docker build --no-cache -t $(APP_NAME) .

run: ## Run container on port configured in `docker.config.env`
	docker run -i -t --rm --env-file=./docker.config.env --platform $(PLATFORM) -p=$(PORT):$(PORT) --name="$(APP_NAME)" $(APP_NAME)

up-dev: bootjar build-dev run ## Run container on port configured in `docker.config.env` (Alias to run)

up-prod: bootjar build-prod run ## Run container on port configured in `docker.config.env` (Alias to run)

stop: ## Stop and remove a running container
	docker stop $(APP_NAME); docker rm $(APP_NAME)

# LOCAL RUN
local-dev:
	wavve_env=dev go run . ### Run go application in local with dev configure

local-prod:
	wavve_env=prod g go run .  ### Run go application in local with prod configure

# RUN TEST
clean-test-cache:
	go clean -testcache

test-run:
	wavve_env=dev go test -v ./app/service/

test-e2e-run:
	wavve_env=dev go test -v ./__test__

test: clean-test-cache test-run

test-e2e: clean-test-cache test-e2e-run

# HELPERS

# generate script to login to aws docker repo
CMD_REPOLOGIN := "eval $$\( aws ecr"
ifdef AWS_CLI_PROFILE
CMD_REPOLOGIN += " --profile $(AWS_CLI_PROFILE)"
endif
ifdef AWS_CLI_REGION
CMD_REPOLOGIN += " --region $(AWS_CLI_REGION)"
endif
CMD_REPOLOGIN += " get-login --no-include-email \)"

# login to AWS-ECR
repo-login: ## Auto login to AWS-ECR unsing aws-cli
	@eval $(CMD_REPOLOGIN)

version: ## Output the current version
	@echo $(VERSION)
