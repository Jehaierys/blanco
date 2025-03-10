package example.com.blanco.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import example.com.blanco.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "users", // "user" table name is forbidden
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"}),
                @UniqueConstraint(columnNames = {"phoneNumber"})
        }
)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(
            schema = "public",
            name = "user_seq_gen",
            sequenceName = "user_seq",
            initialValue = 1,
            allocationSize = 1 // increment by
    )
    private Long id;

    private String email;

    private String phoneNumber;

    private String password;

    private boolean nonLocked;

    private boolean enabled;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.role);
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return email; }

    /*
        Non-locked by creation

        Account might be locked
        in case of criminal activity,
        court order, etc.
     */
    @Override
    public boolean isAccountNonLocked() { return this.nonLocked; }

    /*
        Account is disabled by creation
        Enables after email or phone confirmation
     */
    @Override
    public boolean isEnabled() { return this.enabled; }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Role.USER;
        this.enabled = false;
        this.nonLocked = true;

        this.account = new Account(this);
    }
}
