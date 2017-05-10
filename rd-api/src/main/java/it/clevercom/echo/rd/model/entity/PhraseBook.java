package it.clevercom.echo.rd.model.entity;
// Generated 10-mag-2017 16.45.35 by Hibernate Tools 5.2.2.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * PhraseBook generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_phrase_book"
)
public class PhraseBook  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idphrasebook;
     private User user;
     private String title;
     private String body;

    public PhraseBook() {
    }

	
    public PhraseBook(User user) {
        this.user = user;
    }
    public PhraseBook(User user, String title, String body) {
       this.user = user;
       this.title = title;
       this.body = body;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="phrasebook_idphrasebook_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idphrasebook", unique=true, nullable=false)
    public Long getIdphrasebook() {
        return this.idphrasebook;
    }
    
    public void setIdphrasebook(Long idphrasebook) {
        this.idphrasebook = idphrasebook;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="username", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="title")
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="body")
    public String getBody() {
        return this.body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }




}


