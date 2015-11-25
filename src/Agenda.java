import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

public class Agenda implements IAgenda {

	DateFormat df = new SimpleDateFormat("dd/MM");
	DateFormat df2 = new SimpleDateFormat("MM-dd");
	Calendar calendar = Calendar.getInstance();
	private String path;
	LinkedList<String> DiaSemana = new LinkedList<String>(); // Lista encadeada
	String line; // Array que percorre arquivo recebendo valor do split
	
	public Agenda() {

	}
	
	public void abrirAgenda() throws IOException {
		int op = 0;
		Scanner ler = new Scanner(System.in);
		while(op == 0) {
			System.out.println("1 - Carregar Agenda");
			System.out.println("2 - Visualizar Semana");
			System.out.println("3 - Visualizar Dia da Semana");
			System.out.println("4 - Visualizar Data");
			System.out.println("5 - Cadastrar Novo Compromisso");
			System.out.println("6 - Cancela Compromisso");
			System.out.println("7 - Salva Agenda");
			System.out.println("0 -  Sair");
		
			// L� a op��o selecionada pelo usu�rio
			System.out.print("Digite a op��o: ");
			op = ler.nextInt();
		}
		
		switch(op) { // Switch para administrar a op��o escolhida pelo usu�rio
		
			case 1: // Op��o de Carregar
				System.out.println("Insira o nome do usu�rio: ");
				try {
					carregarAgenda(ler.nextLine()); 
				}
				catch(FileNotFoundException e) {
					System.out.println("Voc� informou um nome inv�lido!");
				}
				System.out.println("Carregou.");
				break;
			case 2:
				
		
			default:
				System.out.println("Voc� selecionou uma o��o inv�lida.");
				break;
		}
		
	}

	public void carregarAgenda(String emailUsuario) throws IOException {
		setPath(emailUsuario); // Deixa caminho do arquivo/email global
		try { 
			FileReader fr = new FileReader("C:\\TGB\\"+emailUsuario + ".txt");
			BufferedReader in = new BufferedReader(fr);
			//String line = in.readLine();
			
			//while (in.ready() == true) {
				//line = in.readLine().split(":");
			
				/*for(int i = 0; i < line.length; i++) {
					if(line[i].equals("--")) { // verifica quando termina o compromisso daquele dia pelo separador "--"
						DiaSemana.add(line[i+1]); // Se tiver, pega pr�xima linha
					}
					
					DiaSemana.add(line[i]); // Adiciona na lista, por�m precisa pegar somente a data
				}
				*/
			
				String strData[] = null;
				while ((line = in.readLine()) != null) {
					if(line.contains("data")) {
						strData = line.split(":");
						System.out.println(line);
					}
				}
				
				for(int i =0; i < strData.length; i++) {
					System.out.println(strData[i]);
				}
				

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo n�o encontrado. Criaremos um para voc� =) ");
			File f = new File("C:\\TGB\\"+emailUsuario+".txt");
			f.createNewFile();
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo.");
		}

	}

	public void visualizarSemana() {
		// M�todo incompleto. Precisa ainda adicionar a verifica��o de
		// compromissos nos dias.
		getPrimeiroDiaDaSemana();
		//Pls arranja um jeito de inserir essa data abaixo dentro do for, se tu for ver ela � o primeiro dia da semana.
		System.out.printf(df.format(calendar.getTime()));
		for (int i = 0; i < 6; i++) {
			calendar.add(calendar.DAY_OF_MONTH, 1);
			System.out.printf(" || " + df.format(calendar.getTime()));
			if(procuraCompromisso(df2.format(calendar.getTime())) == 0){ //exibindo o n�mero de ocorr�ncias, pelo menos � pra exibir HUAEHUA testa a�
				System.out.println(" ");
			}else{
				System.out.println(procuraCompromisso(df2.format(calendar.getTime())));
			} 
		}
	}
	
	public int procuraCompromisso(String date) { //m�todo adicional
		//Pls testa a trata as exce��es.
		String line = " ";
		int contCompromissos = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			while ((line = in.readLine()) != null) {
				if (line.contains(date)) {
					contCompromissos++;
				}
				
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return contCompromissos;
	}

	public void getPrimeiroDiaDaSemana() { // m�todo adicional
		calendar.setFirstDayOfWeek(calendar.SUNDAY);
		int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);
	}

	public void visualizarDiaSemana(String diaSemana) {
		// Pensei em um switch pelo fato de ser String; Talvez seja necess�rio
		// um m�todo auxiliar;
		switch (diaSemana) {
		case "Domingo":
			break;
		case "Segunda":
			break;
		case "Ter�a":
			break;
		case "Quarta":
			break;
		case "Quinta":
			break;
		case "Sexta":
			break;
		case "S�bado":
			break;
		default:
			System.out.println("Escreva o nome do dia corretamente");
			break;
		}

	}

	public void visualizarData(int ano, int mes, int dia) {

		/*String line = " ";
		String lineAnterior = " ";
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			while ((line = in.readLine()) != null) {
				if (line.contains(df3.format(ano + "-" + mes + "-" + dia))) {
					lineAnterior = line;
					System.out.println(lineAnterior);
				}
				if (line.contains("inicio:")) {
					System.out.println(line);
				}
				if (line.contains("duracao:")) { 
					System.out.println(line);
				}
				if (line.contains("participantes:")) {
					System.out.println(line);
					System.out.println("- - - - -");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
	}

	public void novoCompromisso(String descr, int ano, int mes, int dia, int hora, int min, int duracao,
			String[] partic) throws AgendaException {
		// Testa e trata as exce��es pra mim depois pls. Obs: Pode fazer as
		// altera��es necess�rias.
		if (procuraCompromisso(ano, mes, dia, hora, min)) {
			System.out.println("J� tem um compromisso agendado nessa data/hor�rio");
			return;
		}
		if (!(hora >= 8) || !(hora <= 19)) {
			System.out.println("O hor�rio do compromisso precisa estar entre 8h e 19h");
			return;
		}
		if (!(100 % min == 0)) {
			System.out.println("Campo minutos inv�lido");
			return;
		}
		try {
			FileWriter arqv = new FileWriter(path);
			PrintWriter gravarArqv = new PrintWriter(arqv);
			System.out.println("- -");
			gravarArqv.println("descri��o: " + descr + "\ndata: " + ano + "-" + mes + "-" + dia + "\ninicio: " + hora
					+ ":" + min + "\nduracao: " + duracao + " minutos" + "\nparticipantes: " + partic);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public boolean procuraCompromisso(int ano, int mes, int dia, int hora, int min) { // m�todo
																						// adicional
		// testa e trata as exce��o pra mim depois pls. Vlw.
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line = " ";
			while ((line = in.readLine()) != null) {
				if (line.contains(String.valueOf(ano + "-" + mes + "-" + dia))) {
					in.readLine();
					if (line.contains(String.valueOf(hora + ":" + min))) {
						return true;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;

	}

	public void setPath(String path) { // m�todo adicional criado para manter o usu�rio "logado", podendo trabalhar com o login do cliente em outros m�todos.
		this.path = path;
	}

	public void salvar() throws IOException {

	}


	public void cancelarCompromisso(int dia, int mes, int ano, String inicio) {
		visualizarData(dia, mes, ano);
	}

	public void visualizarDiaSemana(int diaSemana) {
		
		
		
	}

}