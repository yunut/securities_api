USE securities_info

CREATE TABLE USER(
     id CHAR(36) PRIMARY KEY,
     chat_id BIGINT NOT NULL,
     username VARCHAR(255),
     status VARCHAR(50),
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     last_active TIMESTAMP,
     UNIQUE(chat_id)
);

CREATE TABLE user_bond_history(
      id CHAR(36) PRIMARY KEY,
      user_id CHAR(36) NOT NULL,
      bond_name VARCHAR(255) NOT NULL,
      bond_rate DECIMAL(5,2) NOT NULL,
      bond_amount DECIMAL(10,2),
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY(user_id) REFERENCES USER(id)
);