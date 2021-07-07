package GUIEntity;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.io.File;

//�ļ����н�����ʽ(��Ҫ�޸�ͼ��)
public class NodeCellRenderer extends DefaultTreeCellRenderer {
	ImageIcon computerIcon = new ImageIcon("img\\computer.png");
    ImageIcon diskIcon = new ImageIcon("img\\a.jfif");
    ImageIcon folderIcon = new ImageIcon("img\\folder.png");

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel,
                                                  boolean expanded,
                                                  boolean leaf, int row,
                                                  boolean hasFocus){
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        TreeNode[] route = node.getPath();
        if(route.length == 1) {
        	computerIcon = new ImageIcon(computerIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        	setIcon(computerIcon);
        }
        if(route.length == 2){
            //���ô���ͼ��
            diskIcon = new ImageIcon(diskIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            setIcon(diskIcon);
        }
        if(route.length > 2){
            //�����ļ���ͼ��
            folderIcon = new ImageIcon(folderIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            setIcon(folderIcon);
        }

        return this;
    }

}