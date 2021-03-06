package it.clevercom.echo.tm.model.entity;
// Generated 11-apr-2017 13.43.21 by Hibernate Tools 5.2.2.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * NurseOrganizationunitId generated by hbm2java
 */
@Embeddable
public class NurseOrganizationunitId  implements java.io.Serializable {


     private Long idnurse;
     private Long idorganizationunit;

    public NurseOrganizationunitId() {
    }

    public NurseOrganizationunitId(Long idnurse, Long idorganizationunit) {
       this.idnurse = idnurse;
       this.idorganizationunit = idorganizationunit;
    }
   


    @Column(name="idnurse", nullable=false)
    public Long getIdnurse() {
        return this.idnurse;
    }
    
    public void setIdnurse(Long idnurse) {
        this.idnurse = idnurse;
    }


    @Column(name="idorganizationunit", nullable=false)
    public Long getIdorganizationunit() {
        return this.idorganizationunit;
    }
    
    public void setIdorganizationunit(Long idorganizationunit) {
        this.idorganizationunit = idorganizationunit;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof NurseOrganizationunitId) ) return false;
		 NurseOrganizationunitId castOther = ( NurseOrganizationunitId ) other; 
         
		 return ( (this.getIdnurse()==castOther.getIdnurse()) || ( this.getIdnurse()!=null && castOther.getIdnurse()!=null && this.getIdnurse().equals(castOther.getIdnurse()) ) )
 && ( (this.getIdorganizationunit()==castOther.getIdorganizationunit()) || ( this.getIdorganizationunit()!=null && castOther.getIdorganizationunit()!=null && this.getIdorganizationunit().equals(castOther.getIdorganizationunit()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdnurse() == null ? 0 : this.getIdnurse().hashCode() );
         result = 37 * result + ( getIdorganizationunit() == null ? 0 : this.getIdorganizationunit().hashCode() );
         return result;
   }   


}


