PHONY: build up

build:
	gradlew.bat clean build

up:
	docker-compose up --build

run: build up