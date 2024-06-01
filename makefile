.PHONY: all clean build start

all: clean build start

clean:
	@echo "Cleaning target directories..."
	cd race_command_service && [ -d target ] && rm -rf target || true
	cd race_query_service && [ -d target ] && rm -rf target || true
	cd race-application-client-application && [ -d build ] && rm -rf build || true

build:
	@echo "Building Spring Boot projects..."
	cd race_command_service && ./mvnw clean package
	cd race_query_service && ./mvnw clean package
	@echo "Building React application..."
	cd race-application-client-application && npm install && npm run build

start:
	@echo "Starting all services using docker-compose..."
	docker-compose up
