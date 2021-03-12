package tech.uinb.tungus.entity;

import java.util.Objects;

public class PledgeExt {
    private Long id;
    private Long extId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExtId() {
        return extId;
    }

    public void setExtId(Long extId) {
        this.extId = extId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PledgeExt stashExt = (PledgeExt) o;
        return Objects.equals(id, stashExt.id) && Objects.equals(extId, stashExt.extId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, extId);
    }

    @Override
    public String toString() {
        return "StashExt{" +
                "id=" + id +
                ", extId=" + extId +
                '}';
    }
}
