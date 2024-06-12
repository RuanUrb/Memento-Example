import java.util.Stack;

//arquivo Editor.java
// classe originaria que abriga o Memento, possui o campo texto cujo historico deseja-se manter
 class Editor {
	private String texto;

	public void setTexto(String texto) {
    	this.texto = texto;
	}

	public String getTexto() {
    	return texto;
	}

// classe memento aninhada, responsável por copiar o estado de sua classe originaria e a partir dele gerar uma copia/versao	
public Memento criarMemento() {
    	return new Memento(texto);
	}

	public void restaurar(Memento memento) {
    	this.texto = memento.getEstado();
	}

	// Classe aninhada Memento
	public static class Memento {
    	private final String estado;

    	private Memento(String estado) {
        	this.estado = estado;
    	}

    	private String getEstado() {
        	return estado;
    	}
	}
}


// arquivo Aplicacao.java
//classe principal de execucao da aplicacao como um todo
public class Aplicacao {
	public static void main(String[] args) {
    	Editor editor = new Editor();

            // a classe principal eh a responsavel por instanciar a estrutura de dados que representa o historico de alteracoes
    	Stack<Editor.Memento> historico = new Stack<>();

    	editor.setTexto("Primeiro texto");
    	historico.push(editor.criarMemento());

    	editor.setTexto("Segundo texto");
    	historico.push(editor.criarMemento());

    	editor.setTexto("Terceiro texto");

    	System.out.println("Estado atual: " + editor.getTexto());

    	editor.restaurar(historico.pop());
    	System.out.println("Estado após desfazer: " + editor.getTexto());

    	editor.restaurar(historico.pop());
    	System.out.println("Estado após desfazer: " + editor.getTexto());
	}
}
