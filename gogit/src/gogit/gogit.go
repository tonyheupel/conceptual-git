// Copyright 2013 Tony Heupel

// Package gogit implements conceptual-git ideas as go libraries and programs.
package gogit

import (
	"errors"
)

const (
)

var 
	ErrInvalidNodeValue = errors.New("gogit: The node must have a value")
)

type CommitValue int

type Commit struct {
	value CommitValue
	parents []Commit
}

