build:
	./mvnw clean package -DskipTests=true

docker.build:
	@make build
	docker build -t docker-redis .

up:
	@make docker.build
	export TAG_COMMIT="docker-redis"
	docker-compose up --build -d

down:
	docker-compose down