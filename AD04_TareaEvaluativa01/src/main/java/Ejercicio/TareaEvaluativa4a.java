package Ejercicio;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import Entidad.Address;
import Entidad.Student;
import Entidad.Tuition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TareaEvaluativa4a {
	/**
	 * 1. OneToOne unidireccional entre entidades Student y Tuition (matr�cula)
	 */
	public static void main(String[] args) {

		// crea factory y entityManager
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ud4");
		EntityManager entityManager = factory.createEntityManager();
		
		try {			
			// crea un objeto Student
			System.out.println("Creando un nuevo objeto Student con su direcci�n y matr�cula (tuition)");
			Student student = createStudent();
			Tuition tuition = new Tuition();
			tuition.setFee(4000.00);
			student.setTuition(tuition);					
			// comienza la transacci�n
			entityManager.getTransaction().begin();
			
			// guarda el objeto Student
			System.out.println("Guardando el estudiante...");
		
			//guarda el Student y con CascadeType.ALL guarda tambi�n el Tuition
			entityManager.persist(student);
			
			// hace commit de la transaccion
			entityManager.getTransaction().commit();
					
			System.out.println("Hecho!");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepci�n
			System.out.println("Realizando Rollback");
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			entityManager.close();
			factory.close();
		}
	}
	private static Student createStudent() {
		Student tempStudent = new Student();
		Address tempAddress = new Address();
		
		tempStudent.setFirstName("Lulu");
		tempStudent.setLastName("Laiur");
		tempStudent.setEmail("llaiur@birt.eus");
		tempStudent.getPhones().add("687155776");
		tempStudent.getPhones().add("694562345");
		tempStudent.setBirthdate(LocalDate.parse("1975-03-04"));
		tempAddress.setAddressLine1("Barian kale 8");
		tempAddress.setAddressLine2("1A");
		tempAddress.setCity("Vitoria");
		tempAddress.setZipCode("01001");
		tempStudent.setAddress(tempAddress);
		return tempStudent;		
	}
}