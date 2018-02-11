package com.beamofsoul.springboot.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName TreeNode
 * @Description 树形结构事例
 * @author MingshuJian
 * @Date 2017年2月24日 上午11:03:40
 * @version 1.0.0
 */
@Setter
@Getter
@NoArgsConstructor

@JsonIgnoreProperties(value={"children"})

@Entity
@Table(name = "T_TREE_NODE")
public class TreeNode extends BaseAbstractEntity  {

	private static final long serialVersionUID = -6164297325194817151L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	
	@Column(name = "NAME", length = 20)
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)  
    @JoinColumn(name = "PARENT_ID")
	private TreeNode parent;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
	private Set<TreeNode> children = new LinkedHashSet<TreeNode>();

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", name=" + name + ", parent=" + parent + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else {
			if (o instanceof TreeNode) {
				TreeNode oNode = (TreeNode) o;
				return oNode.getId() == this.getId();
			} else {
				return false;
			}
		}
	}
	
}
