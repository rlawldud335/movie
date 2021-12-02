package movie.start.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
    @Column(updatable = false)
    private LocalDateTime createTime;
    private LocalDateTime editTime;

    @PrePersist
    public void before(){
        LocalDateTime now = LocalDateTime.now();
        this.createTime = now;
        this.editTime = now;
    }
    @PreUpdate
    public void always(){
        this.editTime = LocalDateTime.now();
    }

    public String display() {
        return "BaseEntity{" +
                "createTime=" + createTime +
                ", editTime=" + editTime +
                '}';
    }
}
