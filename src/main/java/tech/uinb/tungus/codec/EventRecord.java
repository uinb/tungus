package tech.uinb.tungus.codec;

public abstract class EventRecord {
    private Integer moduleIndex;
    private Integer eventIndex;
    private Integer phase;
    private Long extrinsicIdx;
    private Integer topics;

    public Integer getModuleIndex() {
        return moduleIndex;
    }

    public void setModuleIndex(Integer moduleIndex) {
        this.moduleIndex = moduleIndex;
    }

    public Integer getEventIndex() {
        return eventIndex;
    }

    public void setEventIndex(Integer eventIndex) {
        this.eventIndex = eventIndex;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public Long getExtrinsicIdx() {
        return extrinsicIdx;
    }

    public void setExtrinsicIdx(Long extrinsicIdx) {
        this.extrinsicIdx = extrinsicIdx;
    }

    public Integer getTopics() {
        return topics;
    }

    public void setTopics(Integer topics) {
        this.topics = topics;
    }
}

