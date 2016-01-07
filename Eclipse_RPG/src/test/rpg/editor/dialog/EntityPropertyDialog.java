package test.rpg.editor.dialog;

import java.awt.Frame;

import test.rpg.editor.dialog.property.JEventPropertyInt;
import test.rpg.editor.dialog.property.JEventPropertyList;
import test.rpg.editor.dialog.property.JEventPropertyListMonster;
import test.rpg.editor.dialog.property.JEventPropertyString;
import test.rpg.engine.story.event.EventCombat;
import test.rpg.perso.Entity;
import test.rpg.perso.classe.monstre.Rodeur;

public class EntityPropertyDialog extends PropertyDialog<Entity>
{
	private JEventPropertyString name;
	private JEventPropertyInt lvl;
	private JEventPropertyString classe;
	
	public EntityPropertyDialog(Frame parent)
	{
		super(parent, new Entity(), "Monsre", true);
	}

	public EntityPropertyDialog(Frame frame, Entity e)
	{
		super(frame, e, "Monstre", false);
	}

	@Override
	protected void addProperties(Frame frame)
	{
		name = new JEventPropertyString(frame, value.getNom());
		this.addProperty("Nom", name);
		lvl = new JEventPropertyInt(frame, value.getNiveau());
		this.addProperty("Niveau", lvl);
		/*classe = new JEventPropertyString(frame, value.getClasse());
		this.addProperty("Classe", classe);*/
	}

	@Override
	protected Entity setValues()
	{
		return new Entity(name.getValue(), lvl.getValue(), new Rodeur());
	}
}