package data;

import java.util.*;

public final class ProductDatabase {
		
		private ArrayList<Product> productList = new ArrayList<Product>();
		
		public ArrayList<Product> getProductList(){
			return this.productList;
		}
		
		public void addToProductList( Product product ) {
			this.productList.add(product);
		}	
		
		public boolean productChecking( String code ) {
			
				for( Product p: this.productList ) {
						if( p.getProductCode().equals(code) )
							return true;		
				}
				
			return false;
		}
		
		public Product getProduct( String code ) {
			
				for( Product p: this.productList ) {
					if( p.getProductCode().equals(code) )
						return p;
				}
				
			return null;
		}
}
