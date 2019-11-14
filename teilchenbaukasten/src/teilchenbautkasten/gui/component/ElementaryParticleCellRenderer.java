package teilchenbautkasten.gui.component;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import teilchenbautkasten.particles.ElementaryParticle;

public class ElementaryParticleCellRenderer extends JLabel implements ListCellRenderer<ElementaryParticle>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -383905520094439829L;

		@Override
		public Component getListCellRendererComponent(JList<? extends ElementaryParticle> list, ElementaryParticle teilchen,
				int arg2, boolean true1, boolean true2) {
			
			this.setIcon(new ImageIcon(teilchen.getIcon().getScaledInstance(list.getFixedCellHeight()-3, list.getFixedCellHeight()-3, 100)));
			this.setBackground((true1||true2)?Color.GRAY:list.getBackground());
			this.setText(teilchen.getName());
			
			return this;
		}
}
