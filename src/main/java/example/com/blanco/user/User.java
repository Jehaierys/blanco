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
            name = "user_seq_gen",
            sequenceName = "user_seq",
            allocationSize = 3 // cache
    )
    private Long id;

    private String email;

    private String phoneNumber;

    private String password;

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
}
