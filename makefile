.PHONY: all clean build start

all: clean build start

clean:
	@echo "Cleaning target directories..."
	cd race_command_service && [ -d target ] && rm -rf target || true
	cd race_query_service && [ -d target ] && rm -rf target || true
	cd react_app && [ -d build ] && rm -rf build || true

build:
	@echo "Building Spring Boot projects..."
	cd race_command_service && ./mvnw clean package
	cd race_query_service && ./mvnw clean package

start:
	@echo "Starting all services using docker-compose..."
	docker-compose up
