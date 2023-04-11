package external.api.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOGINFO")
public class LogData implements Serializable{
	

		/**
	 * 
	 */
	private static final long serialVersionUID = 903457671262153718L;
		@Id
		@GeneratedValue
		private Long id;
		@Column
		private String reference_id;
		@Column
		private String conversion_method;
		@Column
		private String date_logged_in;
		public String getReference_id() {
			return reference_id;
		}
		public void setReference_id(String reference_id) {
			this.reference_id = reference_id;
		}
		public String getConversion_method() {
			return conversion_method;
		}
		public void setConversion_method(String conversion_method) {
			this.conversion_method = conversion_method;
		}
		public String getDate_logged_in() {
			return date_logged_in;
		}
		public void setDate_logged_in(String date_logged_in) {
			this.date_logged_in = date_logged_in;
		}

		public LogData(String reference_id, String conversion_method, String date_logged_in) {
			super();
			this.reference_id = reference_id;
			this.conversion_method = conversion_method;
			this.date_logged_in = date_logged_in;
		}
		public LogData() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
