import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

public class Agenda implements IAgenda {
	
	DateFormat df = new SimpleDateFormat("dd/MM");
	private String path;
	
	public Agenda() {
		
		
		
	}
	
	public void carregarAgenda(String emailUsuario) throws IOException {
		try {
			FileReader fr = new FileReader(emailUsuario+".txt");
			BufferedReader in = new BufferedReader(fr);
			String line = in.readLine();
			while (line != null) {
				System.out.println(line);
				line = in.readLine();
			}
			String txt[] = line.split("/");
			for(int i = 0; i < txt.length; i++) {
				System.out.println(txt[i]);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado.");
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo.");
		}
		
	}
	
	public void visualizarSemana() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(calendar.SUNDAY);
		int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);
		System.out.println(df.format(calendar.getTime()));
	}
	
	//Coloquei como string porque, se tu ver no PDF do trabalho, o professor cita os nomes dos dias. Mas pode ser que não seja assim.
	public void visualizarDiaSemana(String diaSemana) {
		//Pensei em um switch pelo fato de ser String; Talvez seja necessário um método auxiliar;
		switch(diaSemana){
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
	
	public void novoCompromisso(String descr, int ano, int mes, int dia,
	int hora, int min, int duracao, String[] partic) throws AgendaException {
		//O Eclipse pediu pra eu adicionar um try/catch ou um throws
		//Como eu não manjo muito de Exceptions dá uma olhada nisso pra
		//mim depois pls;
		try {
			FileWriter arqv = new FileWriter(path);
			PrintWriter gravarArqv = new PrintWriter(arqv);
			System.out.println("- -");
			gravarArqv.println("descrição: "+descr+
							  "\ndata: "+ano+"-"+mes+"-"+dia+
							  "\ninicio: "+hora+":"+min+
							  "\nduracao: "+duracao+" minutos"+
							  "\nparticipantes: "+partic);
			//Ainda falta verificar se ja existe um compromisso
			//"Caso Exista, solicitar nova data e horario
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

	public void setPath(String path){
		this.path = path;
	}
	
	public void salvar() throws IOException {
		
	}
	
	//  Método louco
	
	public void cancelarCompromisso(String data, String inicio) {

		String datas[] = data.split("/");
		//VisualizaAgenda v = new VisualizaAgenda();
		//v.visualizarData(Integer.parseInt(datas[0]), Integer.parseInt(datas[1]), Integer.parseInt(datas[2]));

	}

	public void visualizarDiaSemana(int diaSemana) {

		
	}


}