import java.io.IOException;
import java.util.Scanner;

public class Teste {

	public static void main(String[] args) throws IOException {
		
		Agenda agenda = new Agenda();
		
		Scanner ler = new Scanner(System.in);
		
		System.out.print("Digite o nome arquivo: ");
		agenda.carregarAgenda(ler.nextLine());
		
		agenda.visualizarSemana();

	}

}

