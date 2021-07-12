package com.example.demo.model;

import lombok.*;

import java.math.BigDecimal;

import javax.persistence.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="sample")
public class sampleEntity {

	@Id
	private BigDecimal apcno;
	
	private String samplecol;
	
		public BigDecimal getApcno() {
			return apcno;
		}
		
		public void setApcno(BigDecimal apcno) {
			this.apcno = apcno;
		}
		
		public String getSamplecol() {
			return samplecol;
		}
		
		public void setSamplecol(String samplecol) {
			this.samplecol = samplecol;
		}
		
		@Override
		public String toString() {
			return super.toString();
		}
	
		private sampleEntity() {};
		
		public sampleEntity(BigDecimal apcno, String samplecol) {
			super();
			this.apcno = apcno;
			this.samplecol = samplecol;
		}
}
