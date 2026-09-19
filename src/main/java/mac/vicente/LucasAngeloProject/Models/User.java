package mac.vicente.LucasAngeloProject.Models;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@Entity
@Table(name = User.tableName)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User
{

    public interface CreateUser{}
    public interface UpdateUser{}

    public static final String tableName = "users";
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private String id;

    @Column(name = "username", nullable = false)
    @NotNull(groups = CreateUser.class)
    @NotEmpty
    @Size(min = 1, max = 50, groups = UpdateUser.class)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="password", nullable = false, length = 60 )
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 50)
    private String password;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
