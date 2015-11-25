import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

public class Agenda implements IAgenda {

	DateFormat df = new SimpleDateFormat("dd/MM");
	DateFormat df2 = new SimpleDateFormat("MM-dd");
	DateFormat df3 = new SimpleDateFormat("YYYY-MM-dd");
	Calendar calendar = Calendar.getInstance();
	private String path;
	LinkedList<String> Dias = new LinkedList<String>(); // Lista encadeada
	//String line; // String que percorre arquivo recebendo valor do split

	public Agenda() {

	}

	public void abrirAgenda() throws IOException {
		int op = 0;
		Scanner ler = new Scanner(System.in);
		while (op != 0) {
			System.out.println("1 - Carregar Agenda");
			System.out.println("2 - Visualizar Semana");
			System.out.println("3 - Visualizar Dia da Semana");
			System.out.println("4 - Visualizar Data");
			System.out.println("5 - Cadastrar Novo Compromisso");
			System.out.println("6 - Cancela Compromisso");
			System.out.println("7 - Salva Agenda");
			System.out.println("0 -  Sair");

			// Lê a opção selecionada pelo usuário
			System.out.print("Digite a opção: ");
			op = ler.nextInt();
		}

		switch (op) { // Switch para administrar a opção escolhida pelo usuário

		case 1: // Opção de Carregar
			System.out.println("Insira o nome do usuário: ");
			try {
				carregarAgenda(ler.nextLine());
			} catch (FileNotFoundException e) {
				System.out.println("Você informou um nome inválido!");
			}
			System.out.println("Carregou.");
			abrirAgenda();
			break;
		case 2:
			visualizarSemana();
			abrirAgenda();
			break;
		case 3:
			visualizarDiaSemana(ler.nextInt()); //método não está feito
			abrirAgenda();
			break;
		case 4:
			System.out.println("Ano:");
			int ano = ler.nextInt();
			
			System.out.println("Mês:");
			int mes = ler.nextInt();
			
			System.out.println("Dia:");
			int dia = ler.nextInt();
			
			visualizarData(ano, mes, dia);
			abrirAgenda();
			break;
		case 5:
			System.out.println("Descrição:");
			String desc = ler.nextLine();
			System.out.println("Ano:");
			int ano2 = ler.nextInt();
			System.out.println("Mês:");
			int mes2 = ler.nextInt();
			System.out.println("Dia:");
			int dia2 = ler.nextInt();
			System.out.println("Digite a hora de início e, após, os minutos:");
			int hora = ler.nextInt();
			int min = ler.nextInt();
			System.out.println("Duração em minutos:");
			int duracao = ler.nextInt();
			System.out.println("Digite o número de paticipantes (Use 0 se não houver nenhum):");
			String partic[];
			int aux;
			if((aux = ler.nextInt()) == 0){
				partic = null;
			}else{
				partic = new String[aux];
				for(int i = 0; i < partic.length; i++){
					System.out.println("Nome do "+aux+"º participante:");
					partic[i] = ler.nextLine();
				}
			}
			novoCompromisso(desc, ano2, mes2, dia2, hora, min, duracao, partic);
			abrirAgenda();
		case 6:
			System.out.println("Ano:");
			int ano3 = ler.nextInt();
			System.out.println("Mes:");
			int mes3 = ler.nextInt();
			System.out.println("Dia:");
			int dia3 = ler.nextInt();
			System.out.println("Horas seguidas de minutos de início do evento que desejas cancelar:");
			int inicio = ler.nextInt();
			int minInicio = ler.nextInt();
			
			cancelarCompromisso(ano3, mes3, dia3, inicio, minInicio); //não feito
			abrirAgenda();
		case 7:
			salvar(); //não feito
			abrirAgenda();
		default:
			System.out.println("Você selecionou uma oção inválida. Tente novamente.");
			abrirAgenda();
			break;
		}
		ler.close();

	}

	public void carregarAgenda(String emailUsuario) throws IOException {
		setPath(emailUsuario); // Deixa caminho do arquivo/email global
		try {
			FileReader fr = new FileReader("C:\\TGB\\" + emailUsuario + ".txt");
			BufferedReader in = new BufferedReader(fr);
			String line;

			// while (in.ready() == true) {
			// line = in.readLine().split(":");

			/*
			 * for(int i = 0; i < line.length; i++) { if(line[i].equals("--")) {
			 * // verifica quando termina o compromisso daquele dia pelo
			 * separador "--" DiaSemana.add(line[i+1]); // Se tiver, pega
			 * próxima linha }
			 * 
			 * DiaSemana.add(line[i]); // Adiciona na lista, porém precisa pegar
			 * somente a data }
			 */

			/* String strData[] = null;
			while ((line = in.readLine()) != null) {
				if (line.contains("data")) {
					strData = line.split("-");
					//System.out.println(line);
				}
			}
			
			for (int i = 0; i < strData.length; i++) {
				System.out.println(strData[i]);
			}
			*/
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado. Criaremos um para você =) ");
			File f = new File("C:\\TGB\\" + emailUsuario + ".txt");
			f.createNewFile();
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo.");
		}
	}

	public void visualizarSemana() {
		// Método incompleto. Precisa ainda adicionar a verificação de
		// compromissos nos dias.
		getPrimeiroDiaDaSemana();
		System.out.printf(df.format(calendar.getTime()));
		for (int i = 0; i < 6; i++) {
			calendar.add(calendar.DAY_OF_MONTH, 1);
			System.out.printf(" || " + df.format(calendar.getTime()));
			if (procuraCompromisso(df2.format(calendar.getTime())) == 0) { 
				System.out.println(" ");
			} else {
				System.out.println(procuraCompromisso(df2.format(calendar.getTime())));
			}
		}
	}

	public int procuraCompromisso(String date) { // método adicional
		// Pls testa a trata as exceções.
		String line = " ";
		int contCompromissos = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			while ((line = in.readLine()) != null) {
				if (line.contains(date)) {
					contCompromissos++;
				}

			}
			in.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return contCompromissos;
	}

	public void getPrimeiroDiaDaSemana() { // método adicional
		calendar.setFirstDayOfWeek(calendar.SUNDAY);
		int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);
	}

	public void visualizarData(int ano, int mes, int dia) {

		
		String line = " ";
		String lineAnterior = " ";
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			while ((line = in.readLine()) != null) {
				String[] s= line.split(":");
				for(int i = 0; i < s.length; i++) {
					//System.out.println(s[i].toString().trim());
					if (s[i].toString().trim().equals(ano+"-"+0+mes+"-"+dia)) {
						lineAnterior = line;
						System.out.println(lineAnterior);
						
					}
				}
				if (line.contains("inicio:")) {
					//System.out.println(line);
				}
				if (line.contains("duracao:")) {
					//System.out.println(line);
				}
				if (line.contains("participantes:")) {
					//System.out.println(line);
					//System.out.println("- - - - -");
				}
			}
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void novoCompromisso(String descr, int ano, int mes, int dia, int hora, int min, int duracao,
			String[] partic) throws AgendaException {
		if (procuraCompromisso(ano, mes, dia, hora, min)) {
			System.out.println("Já tem um compromisso agendado nessa data/horário");
			novoCompromisso(descr, ano, mes, dia, hora, min, duracao, partic);
		}
		if (!(hora >= 8) || !(hora <= 19)) {
			System.out.println("O horário do compromisso precisa estar entre 8h e 19h");
			novoCompromisso(descr, ano, mes, dia, hora, min, duracao, partic);
		}
		if (!(100 % min == 0)) {
			System.out.println("Campo minutos inválido");
			novoCompromisso(descr, ano, mes, dia, hora, min, duracao, partic);
		}
		try {
			FileWriter arqv = new FileWriter(path);
			PrintWriter gravarArqv = new PrintWriter(arqv);
			System.out.println("- -");
			gravarArqv.println("descrição: " + descr + "\ndata: " + ano + "-" + mes + "-" + dia + "\ninicio: " + hora
					+ ":" + min + "\nduracao: " + duracao + " minutos" + "\nparticipantes: " + partic);
			gravarArqv.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public boolean procuraCompromisso(int ano, int mes, int dia, int hora, int min) { // Método Adiciona
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
				in.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;

	}
	
	public void setPath(String path) { // método adicional criado para manter o usuário "logado", podendo trabalhar com o login do cliente em outros métodos.
		this.path = "C:\\TGB\\" + path + ".txt";
	}

	public void salvar() throws IOException {

	}

	public void cancelarCompromisso(int dia, int mes, int ano, int hora, int min) { //adicionei um parâmetro para aproveitar o método adicional "procuraCompromisso"
		visualizarData(dia, mes, ano);
	}

	public void visualizarDiaSemana(int diaSemana) {

	}

} 