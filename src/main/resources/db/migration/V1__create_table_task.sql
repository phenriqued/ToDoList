CREATE TABLE tb_task (
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    text    VARCHAR(255),
    done    BOOLEAN,
    favorite BOOLEAN
);
