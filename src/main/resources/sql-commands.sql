create table users (
    id serial not null primary key,
    username VARCHAR(50) unique not null,
    password VARCHAR(255) not null,
    role varchar(255) not null
)

CREATE TABLE packages (
    id serial not null primary key,
    track_code varchar(50) unique not null,  -- código de rastreio
    description varchar(255),
    sender varchar(100),
    receiver varchar(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'pending', -- pending, in_transit, delivered
    user_id INT REFERENCES users(id) ON DELETE SET NULL -- opcional, dono do pacote
)

CREATE TABLE package_events (
    id SERIAL PRIMARY KEY,
    package_id INT REFERENCES packages(id) ON DELETE CASCADE,
    location varchar(100),
    status varchar(50) NOT NULL,  -- pending, in_transit, delivered, etc
    event_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notes TEXT
)