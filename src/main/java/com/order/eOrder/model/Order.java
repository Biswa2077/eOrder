package com.order.eOrder.model;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("eorder")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey("id")
	private int oid;
	@NotNull
	@Positive
	private int pid;
	@NotNull
	@Positive
	private int qnt;
	@NotNull(message = "Please provide a price")
    @DecimalMin("1.00")
	private float price;
	@NotNull
	@Size(min=2,max=20)
	@NotEmpty(message = "please provide your name...")
	private String cname;
	@NotNull
	@Size(min=2,max=50)
	@NotEmpty(message = "please give the address")
	private String caddress;
	
	public Order(int oid, int pid, int qnt, float price, String cname, String caddress) {
		super();
		this.oid = oid;
		this.pid = pid;
		this.qnt = qnt;
		this.price = price;
		this.cname = cname;
		this.caddress = caddress;
	}
}
