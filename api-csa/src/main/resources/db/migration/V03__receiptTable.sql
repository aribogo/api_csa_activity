CREATE TABLE tb_receipt (
					id BIGINT AUTO_INCREMENT PRIMARY KEY, 
					payment_date DATE,
					file_type VARCHAR(15) NOT NULL,
					file_folder VARCHAR(255) NOT NULL,
					file_size BIGINT,
					file_name VARCHAR(50) NOT NULL,
					month_reference VARCHAR(2) NOT NULL,
					year_reference VARCHAR(4) NOT NULL,
					participant_id BIGINT NOT NULL,
					FOREIGN KEY (participant_id) REFERENCES tb_participant(id)
					);