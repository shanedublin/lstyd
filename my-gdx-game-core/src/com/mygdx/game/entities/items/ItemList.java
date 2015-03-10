package com.mygdx.game.entities.items;


public enum ItemList {
	
		Sword{
		public Item get(){return new Item(1,"Sword", false,"Sword.png");}
		},
		
		Food{
			public Item get(){return new Item(2,"Food", true, "Food.png");}
		},
		
		Ore{
			public Item get(){return new Item(3,"Ore", true, "Ore.png");}
		},
		
		Helmet{
			public Item get(){return new Item(4,"Helmet", true, "Helmet.png");}
		},
		
		ChestPlate{
			public Item get(){return new Item(5,"ChestPlate", true,"Item.png");}
		};

		public  Item get() {
			throw new AbstractMethodError();
		}
		
	
	
}
