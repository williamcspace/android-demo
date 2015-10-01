package tequila.dao.models;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "FIELD".
 */
public class Field {

    private Long id;
    private String name;
    private Integer migrationId;

    public Field() {
    }

    public Field(Long id) {
        this.id = id;
    }

    public Field(Long id, String name, Integer migrationId) {
        this.id = id;
        this.name = name;
        this.migrationId = migrationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMigrationId() {
        return migrationId;
    }

    public void setMigrationId(Integer migrationId) {
        this.migrationId = migrationId;
    }

}
