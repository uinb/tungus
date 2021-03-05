package tech.uinb.tungus.codec.fusotao.type;


public class DispatchError {
    private Integer idx;
    private Integer ModuleIdx;
    private Integer errorIdx;

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Integer getModuleIdx() {
        return ModuleIdx;
    }

    public void setModuleIdx(Integer moduleIdx) {
        ModuleIdx = moduleIdx;
    }

    public Integer getErrorIdx() {
        return errorIdx;
    }

    public void setErrorIdx(Integer errorIdx) {
        this.errorIdx = errorIdx;
    }
}
