package it.clevercom.echo.rd.model.converter;

import java.util.Date;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.AddressDTO;
import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.LocalityDTO;
import it.clevercom.echo.rd.model.dto.PatientDTO;
import it.clevercom.echo.rd.model.entity.Citizenship;
import it.clevercom.echo.rd.model.entity.Country;
import it.clevercom.echo.rd.model.entity.Maritalstatus;
import it.clevercom.echo.rd.model.entity.Municipality;
import it.clevercom.echo.rd.model.entity.OrganizationUnit;
import it.clevercom.echo.rd.model.entity.Patient;

public class Patient2PatientDTO implements CustomConverter, MapperAware {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Patient) {
			PatientDTO target = null; 
			Patient source = (Patient) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new PatientDTO();
			} else {
				target = (PatientDTO) destinationFieldValue;
			}
			
			// disclaimer: spaghetti code here
			
			// map standard patient data
			target.setIdPatient((source.getIdpatient() != null) ? source.getIdpatient() : null);
			target.setName((source.getName() != null) ? source.getName() : null);
			target.setSurname((source.getSurname() != null) ? source.getSurname() : null);
			target.setDateOfBirth((source.getDateofbirth() != null) ? Long.valueOf(source.getDateofbirth().getTime()) : null);
			target.setDeathDate((source.getDeathdate() != null) ? Long.valueOf(source.getDeathdate().getTime()) : null);
			target.setEmail((source.getEmail() != null) ? source.getEmail() : null);
			target.setGender((source.getGender() != null) ? source.getGender() : null);
			target.setHealthCode((source.getHealthcode() != null) ? source.getHealthcode() : null);
			target.setFullname((source.getFullname() != null) ? source.getFullname() : null);
			target.setPhoneNumber((source.getPhonenumber() != null) ? source.getPhonenumber() : null);
			target.setTaxCode((source.getTaxcode() != null) ? source.getTaxcode() : null);
			
			// map related complex object
			target.setCitizenship((source.getCitizenship()!=null) ? rdDozerMapper.map(source.getCitizenship(), BaseObjectDTO.class) : null);
			target.setExtOrganizationUnit((source.getOrganizationUnitByIdextorganizationunit()!=null) ? rdDozerMapper.map(source.getOrganizationUnitByIdextorganizationunit(), BaseObjectDTO.class) : null);
			target.setIntOrganizationUnit((source.getOrganizationUnitByIdintorganizationunit()!=null) ? rdDozerMapper.map(source.getOrganizationUnitByIdintorganizationunit(), BaseObjectDTO.class) : null);
			target.setMaritalStatus((source.getMaritalstatus()!=null) ? rdDozerMapper.map(source.getMaritalstatus(), BaseObjectDTO.class) : null);
			
			/**
			 * convert birthplace data
			 */
			BaseObjectDTO birthPlaceMunicipality = null;
			BaseObjectDTO birthPlaceProvince = null;
			BaseObjectDTO birthPlaceRegion = null;
			BaseObjectDTO birthPlaceCountry = null;
			if (source.getMunicipalityByBirthplaceidmunicipality()!=null) {
				birthPlaceMunicipality = rdDozerMapper.map(source.getMunicipalityByBirthplaceidmunicipality(), BaseObjectDTO.class);
				birthPlaceProvince = rdDozerMapper.map(source.getMunicipalityByBirthplaceidmunicipality().getProvince(), BaseObjectDTO.class);
				birthPlaceRegion = rdDozerMapper.map(source.getMunicipalityByBirthplaceidmunicipality().getProvince().getRegion(), BaseObjectDTO.class);
			} 
			if (source.getCountryByBirthplaceidcountry()!=null) {
				// birthplace country
				birthPlaceCountry = rdDozerMapper.map(source.getCountryByBirthplaceidcountry(), BaseObjectDTO.class);
			}

			LocalityDTO birthPlace = null;
			if ((birthPlaceCountry != null) || (birthPlaceMunicipality != null)) {
				birthPlace = new LocalityDTO(birthPlaceCountry, birthPlaceRegion, birthPlaceProvince, birthPlaceMunicipality);
			}
			
			/**
			 * convert residence data
			 */
			BaseObjectDTO residenceMunicipality = null;
			BaseObjectDTO residenceProvince = null;
			BaseObjectDTO residenceRegion = null;
			BaseObjectDTO residenceCountry = null;
			if (source.getMunicipalityByResidenceidmunicipality()!=null) {		
				residenceMunicipality = rdDozerMapper.map(source.getMunicipalityByResidenceidmunicipality(), BaseObjectDTO.class);
				residenceProvince = rdDozerMapper.map(source.getMunicipalityByResidenceidmunicipality().getProvince(), BaseObjectDTO.class);
				residenceRegion = rdDozerMapper.map(source.getMunicipalityByResidenceidmunicipality().getProvince().getRegion(), BaseObjectDTO.class);
			}
			if (source.getCountryByResidenceidcountry()!=null) {
				// residence country
				residenceCountry = rdDozerMapper.map(source.getCountryByResidenceidcountry(), BaseObjectDTO.class);
			}
			
			AddressDTO residence = null;
			if ((residenceCountry != null) || (residenceMunicipality != null)) {
				residence = new AddressDTO(new LocalityDTO(residenceCountry, residenceRegion, residenceProvince, residenceMunicipality), (source.getResidencestreetaddress()!=null) ? source.getResidencestreetaddress() : null);
			}
		
			/**
			 * convert domicile data
			 */
			BaseObjectDTO domicileMunicipality = null;
			BaseObjectDTO domicileProvince = null;
			BaseObjectDTO domicileRegion = null;
			BaseObjectDTO domicileCountry = null;
			if (source.getMunicipalityByDomicileidmunicipality()!=null) {				 
				 domicileMunicipality = rdDozerMapper.map(source.getMunicipalityByDomicileidmunicipality(), BaseObjectDTO.class);
				 domicileProvince = rdDozerMapper.map(source.getMunicipalityByDomicileidmunicipality().getProvince(), BaseObjectDTO.class);
				 domicileRegion = rdDozerMapper.map(source.getMunicipalityByDomicileidmunicipality().getProvince().getRegion(), BaseObjectDTO.class);
			}
			if (source.getCountryByDomicileidcountry()!=null) {
				// birthplace country
				domicileCountry = rdDozerMapper.map(source.getCountryByDomicileidcountry(), BaseObjectDTO.class);
			}			
			
			AddressDTO domicile = null;
			if ((domicileCountry != null) || (domicileMunicipality != null)) { 
				domicile = new AddressDTO(new LocalityDTO(domicileCountry, domicileRegion, domicileProvince, domicileMunicipality), (source.getDomicilestreetaddress()!=null) ? source.getDomicilestreetaddress() : null);
			}
		
			// inject locality and addresses data
			target.setBirthPlace(birthPlace);
			target.setResidence(residence);
			target.setDomicile(domicile);
			
			// inject tech fields
			target.setActive((source.getActive()!=null) ? source.getActive() : null);
			target.setCreated((source.getCreated()!=null) ? source.getCreated() : null);
			target.setUpdated((source.getUpdated()!=null) ? source.getUpdated() : null);
			target.setUserUpdate((source.getUserupdate()!=null) ? source.getUserupdate() : null);
			
			// return adjusted PatientDTO
			return target.buildExtendedObject();
		} else if (sourceFieldValue instanceof PatientDTO) {
			Patient target = null; 
			PatientDTO source = (PatientDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new Patient();
			} else {
				target = (Patient) destinationFieldValue;
			}
			
			// disclaimer: spaghetti code here
			
			// map standard patient data
			target.setIdpatient((source.getIdPatient() != null) ? source.getIdPatient() : null);
			target.setName((source.getName() != null) ? source.getName() : null);
			target.setSurname((source.getSurname() != null) ? source.getSurname() : null);
			target.setDateofbirth((source.getDateOfBirth() != null) ? new Date(source.getDateOfBirth()) : null);
			target.setDeathdate((source.getDeathDate() != null) ? new Date(source.getDeathDate()) : null);
			target.setEmail((source.getEmail() != null) ? source.getEmail() : null);
			target.setGender((source.getGender() != null) ? source.getGender() : null);
			target.setHealthcode((source.getHealthCode() != null) ? source.getHealthCode() : null);
			target.setFullname((source.getFullname() != null) ? source.getFullname() : null);
			target.setPhonenumber((source.getPhoneNumber() != null) ? source.getPhoneNumber() : null);
			target.setTaxcode((source.getTaxCode() != null) ? source.getTaxCode() : null);
			
			// map related complex object
			target.setCitizenship((source.getCitizenship()!=null) ? rdDozerMapper.map(source.getCitizenship(), Citizenship.class) : null);
			target.setOrganizationUnitByIdextorganizationunit((source.getExtOrganizationUnit()!=null) ? rdDozerMapper.map(source.getExtOrganizationUnit(), OrganizationUnit.class) : null);
			target.setOrganizationUnitByIdintorganizationunit((source.getIntOrganizationUnit()!=null) ? rdDozerMapper.map(source.getIntOrganizationUnit(), OrganizationUnit.class) : null);
			target.setMaritalstatus((source.getMaritalStatus()!=null) ? rdDozerMapper.map(source.getMaritalStatus(), Maritalstatus.class) : null);
			
			/**
			 * convert birthplace data
			 */
			Municipality birthPlaceMunicipality = null;
			Country birthPlaceCountry = null;
			if (source.getBirthPlace()!=null) {
				if (source.getBirthPlace().getMunicipality()!=null) {
					birthPlaceMunicipality = rdDozerMapper.map(source.getBirthPlace().getMunicipality(), Municipality.class);
				}
				if (source.getBirthPlace().getCountry()!=null) {
					birthPlaceCountry = rdDozerMapper.map(source.getBirthPlace().getCountry(), Country.class);
				}
			}

			target.setCountryByBirthplaceidcountry(birthPlaceCountry);
			target.setMunicipalityByBirthplaceidmunicipality(birthPlaceMunicipality);
			
			/**
			 * convert residence data
			 */
			Municipality residenceMunicipality = null;
			Country residenceCountry = null;
			if (source.getResidence()!=null) {
				if (source.getResidence().getLocality().getMunicipality()!=null) {
					residenceMunicipality = rdDozerMapper.map(source.getResidence().getLocality().getMunicipality(), Municipality.class);
				}
				if (source.getResidence().getLocality().getCountry()!=null) {
					residenceCountry = rdDozerMapper.map(source.getResidence().getLocality().getCountry(), Country.class);
				}
			}

			target.setCountryByResidenceidcountry(residenceCountry);
			target.setMunicipalityByResidenceidmunicipality(residenceMunicipality);
			target.setResidencestreetaddress((source.getResidence().getStreet()!=null) ? source.getResidence().getStreet() : null);
			
			/**
			 * convert domicile data
			 */
			Municipality domicileMunicipality = null;
			Country domicileCountry = null;
			if (source.getDomicile()!=null) {
				if (source.getDomicile().getLocality().getMunicipality()!=null) {
					domicileMunicipality = rdDozerMapper.map(source.getDomicile().getLocality().getMunicipality(), Municipality.class);
				}
				if (source.getDomicile().getLocality().getCountry()!=null) {
					domicileCountry = rdDozerMapper.map(source.getDomicile().getLocality().getCountry(), Country.class);
				}
			}

			target.setCountryByDomicileidcountry(domicileCountry);
			target.setMunicipalityByDomicileidmunicipality(domicileMunicipality);
			target.setDomicilestreetaddress((source.getDomicile().getStreet()!=null) ? source.getDomicile().getStreet() : null);
			
			// inject tech fields
			target.setActive((source.getActive()!=null) ? source.getActive() : null);
			target.setCreated((source.getCreated()!=null) ? source.getCreated() : null);
			target.setUpdated((source.getUpdated()!=null) ? source.getUpdated() : null);
			target.setUserupdate((source.getUserUpdate()!=null) ? source.getUserUpdate() : null);
			
			return target;
		} else {
			throw new MappingException("Converter Patient2PatientDTO " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}

	@Override
	public void setMapper(Mapper mapper) {
		rdDozerMapper = mapper;
	}
}
