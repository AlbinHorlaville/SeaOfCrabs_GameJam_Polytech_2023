package visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import automate.*;
import automate.ActionClass.Cell;
import automate.ActionClass.Closest;
import automate.ActionClass.Egg;
import automate.ActionClass.Explode;
import automate.ActionClass.Get;
import automate.ActionClass.GotPower;
import automate.ActionClass.GotStuff;
import automate.ActionClass.Hit;
import automate.ActionClass.Jump;
import automate.ActionClass.KeyFunc;
import automate.ActionClass.Move;
import automate.ActionClass.MyDir;
import automate.ActionClass.Pick;
import automate.ActionClass.Pop;
import automate.ActionClass.Power;
import automate.ActionClass.Protect;
import automate.ActionClass.Store;
import automate.ActionClass.Throw;
import automate.ActionClass.True;
import automate.ActionClass.Turn;
import automate.ActionClass.Wait;
import automate.ActionClass.Wizz;
import info3.game.automata.ast.*;
import info3.game.automata.ast.Action;
import info3.game.automata.ast.BinaryOp;
import info3.game.automata.ast.Category;
import info3.game.automata.ast.Condition;
import info3.game.automata.ast.Direction;
import info3.game.automata.ast.FunCall;
import info3.game.automata.ast.Key;
import info3.game.automata.ast.State;
import info3.game.automata.ast.Transition;
import info3.game.automata.ast.UnaryOp;


public class Visitor implements IVisitor{
	private ArrayList<automate.State> states_done;
	
	public Visitor() {
	}
	
	public automate.State stateDone(State state) {
		for(automate.State s : states_done) {
			if(s.name().equals(state.name))
				return s;
		}
		return null;
	}

	@Override
	public Object visit(Category cat) {
		// TODO Auto-generated method stub
		System.out.println("Im in category : "+ cat.toString());
		return new automate.Category(cat.terminal.content);
	}

	@Override
	public Object visit(Direction dir) {
		// TODO Auto-generated method stub
		System.out.println("Im in direction: "+ dir.toString());
		return new automate.Direction(dir.terminal.content);
	}

	@Override
	public Object visit(Key key) {
		// TODO Auto-generated method stub
		System.out.println("Im in key " + key.toString());
		return new automate.Key(key.terminal.content);
	}

	@Override
	public Object visit(Value v) {
		// TODO Auto-generated method stub
		System.out.println("Im in value "+ v.toString());
		return v.value;
	}

	@Override
	public Object visit(Underscore u) {
		// TODO Auto-generated method stub
		System.out.println("Im in underscore "+ u.toString());
		int rand = new Random().nextInt(states_done.size());
		return states_done.get(rand);
	}

	@Override
	public void enter(FunCall funcall) {
		System.out.println("Im in funcall "+ funcall.toString());
		// TODO Auto-generated method stub		
	}

	@Override
	public Object exit(FunCall funcall, List<Object> parameters) {
		// TODO Auto-generated method stub
		System.out.println("Im in funcall with parameters "+ funcall.toString());
		
		ArrayList<automate.Parameter> l = new ArrayList<automate.Parameter>();
		for(Object o : parameters) {
			l.add((automate.Parameter)o);
		}
		if (funcall.name.equals("Move")) {
			return new Move(l, funcall.percent);
		}
		else if(funcall.name.equals("Key"))
			return new KeyFunc(l);
		else if(funcall.name.equals("Egg"))
			return new Egg(l, funcall.percent);
		else if(funcall.name.equals("Get"))
			return new Get(l, funcall.percent);
		else if(funcall.name.equals("Hit"))
			return new Hit(l, funcall.percent);
		else if(funcall.name.equals("Jump"))
			return new Jump(l, funcall.percent);
		else if(funcall.name.equals("Explode"))
			return new Explode(l, funcall.percent);
		else if(funcall.name.equals("Pick"))
			return new Pick(l, funcall.percent);
		else if(funcall.name.equals("Pop"))
			return new Pop(l, funcall.percent);
		else if(funcall.name.equals("Power"))
			return new Power(l, funcall.percent);
		else if(funcall.name.equals("Protect"))
			return new Protect(l, funcall.percent);
		else if(funcall.name.equals("Store"))
			return new Store(l, funcall.percent);
		else if(funcall.name.equals("Turn"))
			return new Turn(l, funcall.percent);
		else if(funcall.name.equals("Throw"))
			return new Throw(l, funcall.percent);
		else if(funcall.name.equals("Wait"))
			return new Wait(l, funcall.percent);
		else if(funcall.name.equals("Wizz"))
			return new Wizz(l, funcall.percent);
		else if(funcall.name.equals("MyDir"))
			return new MyDir(l);
		else if(funcall.name.equals("Cell"))
			return new Cell(l);
		else if(funcall.name.equals("True"))
			return new True();
		else if(funcall.name.equals("Closest"))
			return new Closest();
		else if(funcall.name.equals("GotPower"))
			return new GotPower();
		else if(funcall.name.equals("GotStuff"))
			return new GotStuff();
		else {
			return null;
		}
	}

	@Override
	public Object visit(BinaryOp operator, Object left, Object right) {
		// TODO Auto-generated method stub
		System.out.println("Im in binary op " + operator.toString());
		automate.FunCall cL = (automate.FunCall)left;
		automate.FunCall cR = (automate.FunCall)right;
		if(operator.operator.equals("&")) {
			return new Conjonction(operator.toString(),cL, cR);
		}
		return new Disjunction(operator.toString(),cL, cR);
	}

	@Override
	public Object visit(UnaryOp operator, Object expression) {
		// TODO Auto-generated method stub
		System.out.println("Im in unary op " + operator.toString());
		return new automate.Negation((automate.FunCall)expression);
	}

	@Override
	public Object visit(State state) {
		// TODO Auto-generated method stub
		System.out.println("Im in state " + state.toString());
		automate.State s = stateDone(state);
		if(s == null) {
			if(state.name.length() == 0)
				s = new automate.StateDeath();
			else if(state.name.equals("_")) {
				int rand = new Random().nextInt(states_done.size());
				s = states_done.get(rand);
			}
			else
				s = new automate.State(state.name);
			states_done.add(s);
		}
		return s;
	}

	@Override
	public void enter(Mode mode) {
		// TODO Auto-generated method stub
		System.out.println("I enter in mode "+ mode.toString());
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object exit(Mode mode, Object source_state, Object behaviour) {
		// TODO Auto-generated method stub
		System.out.println("I exit mode "+ mode.toString());
		automate.State s = ((automate.State)source_state);
		ArrayList<automate.Transition> l = new ArrayList<automate.Transition>();
		for(automate.Transition t : (List<automate.Transition>)behaviour) {
			l.add((automate.Transition)t);
		}
		
		
		s.SetTransitions(l);		
		return s;
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		// TODO Auto-generated method stub
		System.out.println("Im in behavior " + behaviour.toString());
		return transitions;
	}

	@Override
	public void enter(Condition condition) {
		System.out.println("I enter condition "+ condition.toString());
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object exit(Condition condition, Object expression) {
		// TODO Auto-generated method stub
		System.out.println("I enter condition with expression "+ condition.toString());
		return expression;
	}

	@Override
	public void enter(Action action) {
		// TODO Auto-generated method stub
		System.out.println("I enter action " + action.toString());
		
	}

	@Override
	public Object exit(Action action, List<Object> funcalls) {
		// TODO Auto-generated method stub
		System.out.println("I enter action with funcalls "+ action.toString());
		ArrayList<automate.FunCall> l = new ArrayList<automate.FunCall>();
		for(Object f : funcalls) {
			l.add((automate.FunCall)f);
		}
		
		return new automate.Action(l);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object visit(Transition transition, Object condition, Object action, Object target_state) {
		// TODO Auto-generated method stub
		System.out.println("Im in transition condition action target_state "+ transition.toString());
		automate.Condition c = new automate.Condition((automate.FunCall)condition);
		return new automate.Transition(
				c,
				(automate.Action)action,
				(automate.State)target_state
				);
	}

	@Override
	public void enter(Automaton automaton) {
		// TODO Auto-generated method stub
		System.out.println("I enter automaton " + automaton.toString());
		states_done = new ArrayList<>();
		
	}

	@Override
	public Object exit(Automaton automaton, Object initial_state, List<Object> modes) {
		// TODO Auto-generated method stub
		System.out.println("I exit autpmaton " + automaton.toString());
		Automate a = new Automate(automaton.name);
		//automates.add(a);
		a.initial_state = states_done.get(0);
		a.states = states_done;
		System.out.println("================Bienvenue dans le debuggage d'automate================");
		a.print();
		return a;
	}

	@Override
	public Object visit(AST bot, List<Object> automata) {
		// TODO Auto-generated method stub
		System.out.println("Im in ast " + bot.toString());
		
		return automata;
	}

}