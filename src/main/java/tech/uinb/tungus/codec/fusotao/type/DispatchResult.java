package tech.uinb.tungus.codec.fusotao.type;

public class DispatchResult {
    private Integer index;
    private DispatchError dispatchError;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public DispatchError getDispatchError() {
        return dispatchError;
    }

    public void setDispatchError(DispatchError dispatchError) {
        this.dispatchError = dispatchError;
    }
}
