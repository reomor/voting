package reomor.voting.model;

public class DatabaseEntity {
    protected Integer id;

    DatabaseEntity(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
