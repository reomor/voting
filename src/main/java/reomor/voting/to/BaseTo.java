package reomor.voting.to;

import reomor.voting.HasId;

public class BaseTo implements HasId {
    private Integer id;

    public BaseTo() {}

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
