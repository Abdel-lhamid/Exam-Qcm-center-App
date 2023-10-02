package ma.ensaf.Qcmexamcenterbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensaf.Qcmexamcenterbackend.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "qcm_exam_db")
public class UserEntity implements Serializable, UserDetails {
    private static final long serialVersionUID = 6407689839461559517L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String userId;
    @Column(nullable = false)
    private String fullName;
    @Column(name = "email",unique = true, nullable = false)
    private String email;

    @Column
    private String profileImageUrl;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "password", nullable =false)
    private String encryptedPassword;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <ExamEntity> exams;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamRecordEntity> examsTaken;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return encryptedPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
