// Copyright 2013 Tony Heupel

// Package tiglib implements conceptual-git ideas as go libraries
package tiglib

import (
	"errors"
)

const (
)

var (
	ErrInvalidNodeValue = errors.New("tig: The node must have a value")
)

// Commit represents a git commit
type Commit struct {
	Value string
	Parents []*Commit
}

func (c Commit) String()  string {
	var (
		arrow string
		next string
	)

	if (len(c.Parents) > 0) {
		arrow = "->"
		next = c.Parents[0].String()
	}

	return c.Value + arrow + next
}

func NewCommit(value string) *Commit {
	var parents []*Commit
	return &Commit{value, parents}
}

// Branch represents a git branch
type Branch struct {
	Name string
	Head *Commit
}

func (b Branch) String() string {
	commit := b.Head

	output := b.Name + "=>" + commit.String()

	return output
}

// Repo represents a git repository
type Repo struct {
	CurrentBranch Branch
	Branches map[string]Branch
}

func (r Repo) GetHead() *Commit {
	return r.CurrentBranch.Head
}

func (r Repo) String() string {
	return "Repo: " + r.CurrentBranch.String()
}

func (r Repo) Commit(value string) string {
	commit := NewCommit(value)
	oldHead := r.GetHead()

	if oldHead != nil {
		oldHead.Parents = append(oldHead.Parents, commit)
	}

	r.CurrentBranch.Head = commit
	return r.String()
}

func newRepo() Repo {
	var masterBranch Branch
	masterBranch.Name = "master"

	r := Repo{}
	r.Branches = map[string]Branch{
		masterBranch.Name: masterBranch,
	}

	r.CurrentBranch = r.Branches[masterBranch.Name]

	return r
}

func Init() Repo {
	return newRepo()
}

