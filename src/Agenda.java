import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

public class Agenda implements IAgenda {

	DateFormat df = new SimpleDateFormat("dd/MM");
	DateFormat df2 = new SimpleDateFormat("MM-dd");
	Calendar calendar = Calendar.getInstance();
	private String path;

	public Agenda() {

	}

	public void carregarAgenda(String emailUsuario) throws IOException {
		setPath(emailUsuario);
		try { 
			FileReader fr = new FileReader("C:\\TGB\\"+emailUsuario + ".txt");
			BufferedReader in = new BufferedReader(fr);
			String line = in.readLine();
			while (line != null) {
				System.out.println(line);
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado. Criaremos um para você =) ");
			File f = new File("C:\\TGB\\"+emailUsuario+".txt");
			f.createNewFile();
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo.");
		}

	}

	public void visualizarSemana() {
		// Método incompleto. Precisa ainda adicionar a verificação de
		// compromissos nos dias.
		getPrimeiroDiaDaSemana();
		//Pls arranja um jeito de inserir essa data abaixo dentro do for, se tu for ver ela é o primeiro dia da semana.
		System.out.printf(df.format(calendar.getTime()));
		for (int i = 0; i < 6; i++) {
			calendar.add(calendar.DAY_OF_MONTH, 1);
			System.out.printf(" || " + df.format(calendar.getTime()));
			if(procuraCompromisso(df2.format(calendar.getTime())) == 0){ //exibindo o número de ocorrências, pelo menos é pra exibir HUAEHUA testa aí
				System.out.println(" ");
			}else{
				System.out.println(procuraCompromisso(df2.format(calendar.getTime())));
			}
		}
	}
	
	public int procuraCompromisso(String date) { //método adicional
		//Pls testa a trata as exceções.
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

	public void getPrimeiroDiaDaSemana() { // método adicional
		calendar.setFirstDayOfWeek(calendar.SUNDAY);
		int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);
	}

	public void visualizarDiaSemana(String diaSemana) {
		// Pensei em um switch pelo fato de ser String; Talvez seja necessário
		// um método auxiliar;
		switch (diaSemana) {
		case "Domingo":
			break;
		case "Segunda":
			break;
		case "Terça":
			break;
		case "Quarta":
			break;
		case "Quinta":
			break;
		case "Sexta":
			break;
		case "Sábado":
			break;
		default:
			System.out.println("Escreva o nome do dia corretamente");
			break;
		}

	}

	public void visualizarData(int ano, int mes, int dia) {

	}

	public void novoCompromisso(String descr, int ano, int mes, int dia, int hora, int min, int duracao,
			String[] partic) throws AgendaException {
		// Testa e trata as exceções pra mim depois pls. Obs: Pode fazer as
		// alterações necessárias.
		if (procuraCompromisso(ano, mes, dia, hora, min)) {
			System.out.println("Já tem um compromisso agendado nessa data/horário");
			return;
		}
		if (!(hora >= 8) || !(hora <= 19)) {
			System.out.println("O horário do compromisso precisa estar entre 8h e 19h");
			return;
		}
		if (!(100 % min == 0)) {
			System.out.println("Campo minutos inválido");
			return;
		}
		try {
			FileWriter arqv = new FileWriter(path);
			PrintWriter gravarArqv = new PrintWriter(arqv);
			System.out.println("- -");
			gravarArqv.println("descrição: " + descr + "\ndata: " + ano + "-" + mes + "-" + dia + "\ninicio: " + hora
					+ ":" + min + "\nduracao: " + duracao + " minutos" + "\nparticipantes: " + partic);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public boolean procuraCompromisso(int ano, int mes, int dia, int hora, int min) { // método
																						// adicional
		// testa e trata as exceção pra mim depois pls. Vlw.
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

	public void setPath(String path) { // método adicional
		this.path = path;
	}

	public void salvar() throws IOException {

	}

	// Método louco

	public void cancelarCompromisso(int dia, int mes, int ano, String inicio) {
		visualizarData(dia, mes, ano);
	}

	public void visualizarDiaSemana(int diaSemana) {
		
	}

}