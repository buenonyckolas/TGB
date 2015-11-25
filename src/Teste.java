import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Teste {

	public static void main(String[] args) throws IOException {
		
		Agenda agenda = new Agenda();
		
		Scanner ler = new Scanner(System.in);
		
		
		//agenda.abrirAgenda();
		
		System.out.print("Digite o nome arquivo: ");
		agenda.carregarAgenda(ler.nextLine());
		
		//agenda.visualizarSemana();
		
	}

}

