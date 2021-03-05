package tech.uinb.tungus.codec.fusotao.type;

import java.util.ArrayList;
import java.util.List;

public class AuthorityList {
    private List<Authority> authorities = new ArrayList<>();

    private void add(Authority a) {
        authorities.add(a);
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}


