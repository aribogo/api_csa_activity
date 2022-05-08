CREATE TABLE tb_role (
					id BIGINT AUTO_INCREMENT PRIMARY KEY, 
					name VARCHAR(255) NOT NULL
					);


CREATE TABLE tb_user (
					id BIGINT AUTO_INCREMENT PRIMARY KEY, 
					email VARCHAR(255) NOT NULL,
					password VARCHAR(255) NOT NULL,
					role_id BIGINT NOT NULL,
					FOREIGN KEY (role_id) REFERENCES tb_role(id)
					);
					
CREATE INDEX email ON tb_user (email); 
					
