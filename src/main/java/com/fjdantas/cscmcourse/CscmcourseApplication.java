package com.fjdantas.cscmcourse;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fjdantas.cscmcourse.domain.Address;
import com.fjdantas.cscmcourse.domain.Category;
import com.fjdantas.cscmcourse.domain.City;
import com.fjdantas.cscmcourse.domain.Client;
import com.fjdantas.cscmcourse.domain.OrderItem;
import com.fjdantas.cscmcourse.domain.Payment;
import com.fjdantas.cscmcourse.domain.PaymentWithCard;
import com.fjdantas.cscmcourse.domain.PaymentWithTicket;
import com.fjdantas.cscmcourse.domain.Product;
import com.fjdantas.cscmcourse.domain.State;
import com.fjdantas.cscmcourse.domain.PurchaseOrder;
import com.fjdantas.cscmcourse.domain.enums.StatusPayment;
import com.fjdantas.cscmcourse.domain.enums.TypeClient;
import com.fjdantas.cscmcourse.repositories.AddressRepository;
import com.fjdantas.cscmcourse.repositories.CategoryRepository;
import com.fjdantas.cscmcourse.repositories.CityRepository;
import com.fjdantas.cscmcourse.repositories.ClientRepository;
import com.fjdantas.cscmcourse.repositories.OrderItemRepository;
import com.fjdantas.cscmcourse.repositories.PaymentRepository;
import com.fjdantas.cscmcourse.repositories.ProductRepository;
import com.fjdantas.cscmcourse.repositories.PurchaseOrderRepository;
import com.fjdantas.cscmcourse.repositories.StateRepository;

/*
 * class implements interface CommandLineRunner for objects instantiation in the initialization of the application
 */
@SpringBootApplication
public class CscmcourseApplication implements CommandLineRunner{

	/*
	 * dependencies for call operation of data access in the repository layer with auto instance by Spring with the Autowired annotation
	 */
	@Autowired
	private CategoryRepository categoryRepository;	
	@Autowired
	private ProductRepository productRepository;	
	@Autowired
	private StateRepository stateRepository;	
	@Autowired
	private CityRepository cityRepository;	
	@Autowired
	private ClientRepository clientRepository;	
	@Autowired
	private AddressRepository addressRepository;	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;	
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CscmcourseApplication.class, args);
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 * method to instantiating objects and saving values in the database of the system
	 */
	@Override
	public void run(String... args) throws Exception {
		
		//instantiating the objects and defining their values
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
				
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		//making the association between entities
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
			
		//saving objects list in the database with a auto list into saveAll method
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City cit1 = new City(null, "Uberlândia", st1);
		City cit2 = new City(null, "São Paulo", st2);
		City cit3 = new City(null, "Campinas", st2);
		
		st1.getCities().addAll(Arrays.asList(cit1));
		st2.getCities().addAll(Arrays.asList(cit2, cit3));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(cit1, cit2, cit3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", TypeClient.PHYSICALPERSON);
		cli1.getTelephone().addAll(Arrays.asList("27363323", "93838393"));
		
		Address ed1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, cit1);
		Address ed2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cit2);
		cli1.getAddress().addAll(Arrays.asList(ed1, ed2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(ed1, ed2));
		
		//object to format the value of the instant that will be generated in the database
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		//instantiating of the objects of PurchaseOrder
		PurchaseOrder po1 = new PurchaseOrder(null, sdf.parse("30/09/2017 10:32"), cli1, ed1);
		PurchaseOrder po2 = new PurchaseOrder(null, sdf.parse("10/10/2017 19:35"), cli1, ed2);
		
		//instantiating objects of the type Payment through the subclass and realizing the payment
		Payment pay1 = new PaymentWithCard(null, StatusPayment.SETTLED, po1, 6);
		po1.setPayment(pay1);
		Payment pay2 = new PaymentWithTicket(null, StatusPayment.PENDING, po2, sdf.parse("20/10/2017 00:00"), null);
		po2.setPayment(pay2);

		cli1.getOrders().addAll(Arrays.asList(po1, po2));
		
		purchaseOrderRepository.saveAll(Arrays.asList(po1, po2));		
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		OrderItem oi1 = new OrderItem(po1, p1, 0.00, 1, 2000.00);
		OrderItem oi2 = new OrderItem(po1, p3, 0.00, 2, 80.00);
		OrderItem oi3 = new OrderItem(po2, p2, 100.00, 1, 800.00);
		
		po1.getItems().addAll(Arrays.asList(oi1, oi2));
		po2.getItems().addAll(Arrays.asList(oi3));
		
		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
	}
}
