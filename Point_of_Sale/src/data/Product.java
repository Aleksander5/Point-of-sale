package data;

public final class Product {
		
		private String name;
		private String code;
		private float price = 0.00f;
		
		public Product ( String name, String code, float price ) {
				this.name = name;
				this.code = code;
				this.price = price;
		}
		
		public String getProductName() {
			return this.name;
		}
		
		public String getProductCode() {
			return this.code;
		}
		
		public float getProductPrice() {
			return this.price;
		}
		
		public String productToString(){
			String s = String.format("Name: %s, Price: %.2f", this.name, this.price);
			
			return s;
		}
		
}
