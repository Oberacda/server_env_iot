DROP TABLE IF EXISTS env;
CREATE TABLE env(id BIGSERIAL PRIMARY KEY, timestamp TIMESTAMP, temperature DOUBLE PRECISION, humidity DOUBLE PRECISION, pressure DOUBLE PRECISION, illuminance DOUBLE PRECISION, uva DOUBLE PRECISION, uvb DOUBLE PRECISION, uv_index DOUBLE PRECISION);
