CREATE TABLE tb_csa (id BIGINT AUTO_INCREMENT PRIMARY KEY, 
					name VARCHAR(255) NOT NULL
					 );
					 
CREATE TABLE tb_participant (id BIGINT AUTO_INCREMENT PRIMARY KEY, 
					name VARCHAR(255) NOT NULL, 
					email VARCHAR(255) NOT NULL, 
					position VARCHAR(10) NOT NULL,
					id_user BIGINT NOT NULL
					 );
					 
CREATE TABLE tb_csa_participant (
					id BIGINT AUTO_INCREMENT PRIMARY KEY, 
					start_date DATE,
					end_date DATE,
					csa_id BIGINT NOT NULL,
					participant_id BIGINT NOT NULL,
					FOREIGN KEY (csa_id) REFERENCES tb_csa(id),
					FOREIGN KEY (participant_id) REFERENCES tb_participant(id)
					);
					 