DROP TABLE IF EXISTS env;
CREATE TABLE IF NOT EXISTS env(id BIGINT AUTO_INCREMENT PRIMARY KEY, timestamp TIMESTAMP, temperature DOUBLE PRECISION, humidity DOUBLE PRECISION, pressure DOUBLE PRECISION, illuminance DOUBLE PRECISION, uva DOUBLE PRECISION, uvb DOUBLE PRECISION, uv_index DOUBLE PRECISION);
