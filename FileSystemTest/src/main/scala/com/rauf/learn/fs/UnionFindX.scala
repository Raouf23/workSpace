package com.rauf.learn.fs

import scala.collection.mutable._
import scala.annotation.tailrec

class UnionFindX(val len: Int) {

  private case class Node(var parent: Int, var treeSize: Int)

  private val nodes = {
    val nodes = Array.fill[Node](len)(Node(-1, 1))
    var i = 0
    for (node <- nodes) {
      node.parent = i
      i = i + 1
    }
    nodes
  }

  @tailrec
  private def root(i: Int, iterCount: Int): Int = {
    if (nodes(i).parent == i) {
      i
    }
    else {
      //nodes(i).parent = nodes(nodes(i).parent).parent
      root(nodes(i).parent, iterCount + 1)

    }
  }

  def connected(p: Int, q: Int): Boolean = {
    p == q || root(p, 0) == root(q, 0)
  }

  def printNodes(): Unit = {
    // nodes.foreach(x => print("(" + x.parent + ", " + x.treeSize + ")"))
    nodes.foreach(x => print(x.parent + " "))
  }

  def union(p: Int, q: Int) = {
    val rootp = root(p, 0)
    val rootq = root(q, 0)

    if (rootp != rootq) {
      val nodep = nodes(rootp)
      val nodeq = nodes(rootq)

      if (nodep.treeSize < nodeq.treeSize) {
        nodep.parent = rootq
        nodeq.treeSize += nodep.treeSize
      } else {
        nodeq.parent = rootp
        nodep.treeSize += nodeq.treeSize
      }
    }
  }

  def getAllUnions[T](items: List[T]): List[List[T]] = {
    val hm = new HashMap[Int, ListBuffer[T]]
    for (i <- 0 until nodes.length) {
      val ab = hm.get(nodes(i).parent).getOrElse(null)
      if (ab == null) {
        val ab = new ListBuffer[T]
        ab += items(i)
        hm.put(nodes(i).parent, ab)
      }
      else
        ab += items(i)
    }
    val allUnions = new ListBuffer[List[T]]
    for ((key, value) <- hm) {
      allUnions += value.toList
    }
    allUnions.toList
  }
}



object UnionFindX {
   def main(args: Array[String]) = {
    val a = List((1, 1), (1, 2), (3, 2), (3, 4), (5, 4), (19, 20), (5, 6), (7, 6), (7, 8), (9, 8), (9, 10), (11, 10), (11, 12))
    val len = a.length

    val uf = new UnionFindX(len)

    var i = 0
    while(i < len) {
      var j = i + 1

      while(j < len) {
        if (isEqual(a(i), a(j))) uf.union(i, j)
        j = j + 1
      }
      i = i + 1
    }

    uf.printNodes

    val hm = new HashMap[Int, ListBuffer[(Int, Int)]]
    for (i <- 0 until uf.nodes.length) {
      val ab = hm.get(uf.nodes(i).parent).getOrElse(null)
      if (ab == null) {
        val ab = new ListBuffer[(Int, Int)]
        ab += a(i)
        hm.put(uf.nodes(i).parent, ab)
      }
      else
        ab += a(i)
    }

    for ((key, value) <- hm) {
      println(key + " -> " + value.mkString(","))
    }
  }

 def isEqual(a: (Int, Int), b: (Int, Int)): Boolean = {
    if (a._1 == b._1 || a._2 == b._2) return true
    false
  }
}

