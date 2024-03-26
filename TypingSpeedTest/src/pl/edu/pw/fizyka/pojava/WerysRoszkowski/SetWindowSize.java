package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.Dimension;
import java.awt.Toolkit;

public class SetWindowSize {
	int autoWindowHeight = 500; //Przykładowe wartości aby zainicjalizować zmienne.
	int autoWindowWidth = 500;
	
	public SetWindowSize() {
		//PARAMETRY ROZMIARU I PROPORCJI OKNA:
		double heightPercentage = 0.5;	//Jaką część ekranu w pionie ma zająć okno?
		double aspectRatio = 1.8;	//Jaka ma być proporcja szerokości okna do jego wysokości?
		
		
		
		//kod odpowiedzialny za zczytywanie rozmiaru ekranu użytkownika oraz ustawianie odpowiedniego rozmiaru okna - Mateusz
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		autoWindowHeight = (int) (heightPercentage * screenSize.height);
		autoWindowWidth =(int) (autoWindowHeight*aspectRatio);
	}

	int getAutoWindowWidth(){
		return autoWindowWidth;
	}
	
	int getAutoWindowHeigth() {
		return autoWindowHeight;
	}
	
}
