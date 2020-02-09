package ru.mail.ilya6697089.app.service.constant;

public enum CreateActionEnum {
    CREATE_ROLE_TABLE("CREATE TABLE role\n" +
            "(\n" +
            "    id       INT   PRIMARY KEY AUTO_INCREMENT, \n" +
            "    name          VARCHAR(6) NOT NULL,\n" +
            "    description   VARCHAR(60) NOT NULL \n" +
            ");"),
    CREATE_USER_TABLE("CREATE TABLE user\n" +
            "(\n" +
            "    username      VARCHAR(40) NOT NULL,\n" +
            "    password      VARCHAR(40) NOT NULL,\n" +
            "    createdBy     VARCHAR(40) NOT NULL,\n" +
            "    user_id       INT  AUTO_INCREMENT PRIMARY KEY ,\n" +
            "    role_id       INT   ,\n" +
            "    FOREIGN KEY (role_id) REFERENCES role(id) \n" +
            ");"),
    CREATE_ROLE_1("INSERT INTO role(id, name, description) VALUES (1,'USER','User description');"),

    CREATE_ROLE_2("INSERT INTO role(id, name, description) VALUES (2,'ADMIN','Admin description');"),

    CREATE_USER_1("INSERT INTO user(username, password, createdBy,role_id) VALUES ('Vanya','1234','01.02.2020',1);"),

    CREATE_USER_2("INSERT INTO user(username, password, createdBy,role_id) VALUES ('Petya','1111','02.02.2020',2);");

    private final String query;

    CreateActionEnum(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
