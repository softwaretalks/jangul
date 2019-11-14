package com.softwaretalks.jangul.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({EmptyValueGroup.class, Default.class, })
public interface ValidationSequence {
}
